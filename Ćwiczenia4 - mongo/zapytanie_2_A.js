//convert all the credit.balance strings to floats

db.people.find({"credit.balance": { "$exists": true, "$type": 2 }}).forEach(function(doc){
    var credit = doc.credit,
        updateOperatorDocument = {};
    for (var idx = 0; idx < credit.length; idx++){
        var val;
            val = !isNaN(credit[idx].balance) ? parseFloat(credit[idx].balance) : credit[idx].balance;
            updateOperatorDocument["credit."+ idx +".balance"] = val;
    };
    db.people.updateOne(
        { "_id": doc._id },
        { "$set": updateOperatorDocument }
    );
});

db.people.aggregate([
	{$unwind: '$credit'},
	{$group: {
		_id: '$credit.currency', balance: {$sum: '$credit.balance'} }
	},
	{$project: {
		_id: 0, currency: '$_id', total_balance: '$balance'}
		}, {$sort: {total_balance: -1} }
]).pretty()

