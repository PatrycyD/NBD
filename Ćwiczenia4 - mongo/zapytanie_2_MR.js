var mapFunc = function(){
var credit = this.credit;
	for(i in credit){
	emit(credit[i].currency, credit[i].balance);
	}
};

var reduceFunc = function(currency, balance){
return Array.sum(balance);
};

db.people.mapReduce(mapFunc, reduceFunc, {out: 'MapReduce_2'});

db.MapReduce_2.aggregate({$project: {currency: '$_id', total_balance: '$value', _id: 0} }, {$sort: {total_balance: -1} }).pretty()
//db.MapReduce_2.find().pretty()
