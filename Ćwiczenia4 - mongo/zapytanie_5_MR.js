var mapFunc = function(){
var credit = this.credit;
	for(i in credit){
	emit(credit[i].currency, {balance: credit[i].balance, count: 1} );
	}
};

var reduceFunc = function(currency, values){
	var balances = {total_balance: 0 , count: 0}
	for (i in values){
		balances.total_balance += values[i].balance;
		balances.count += values[i].count;
	};
	return balances;
};

var finalizeFunc = function(currency, values){
	values.average = (values.total_balance / values.count);
	return {total_balance: values.total_balance, avg_balance: values.average};
};

db.people.mapReduce(
mapFunc,
reduceFunc,
{query: {sex: 'Female', nationality: 'Poland'},
finalize: finalizeFunc,
out: 'MapReduce_5'}
)

db.MapReduce_5.aggregate([
	{$unwind: '$value'},
	{$project:
		{_id: 0,
		currency: '$_id',
		total_balance: {$round: ['$value.total_balance', 2] },
		avg_balance: {$round: ['$value.avg_balance', 2] } }
	},
	{$sort: {total_balance: -1} }
]).pretty()
