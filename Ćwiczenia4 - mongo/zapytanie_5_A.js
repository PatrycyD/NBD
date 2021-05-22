db.people.aggregate([
	{$match:
		{sex: 'Female',
		nationality: 'Poland'}
	},
	{$unwind: '$credit'},
	{$group:
		{_id: '$credit.currency',
		total_balance: {$sum: '$credit.balance'},
		avg_balance: {$avg: '$credit.balance'}
		}
	},
	{$project:
		{_id: 0,
		currency: '$_id',
		total_balance: {$round: ['$total_balance', 2] },
		avg_balance: {$round: ['$avg_balance', 2] } }
	},
	{$sort: {total_balance : -1} }
])
