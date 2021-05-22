db.people.aggregate([
{$group: {
	_id: '$sex',
	avg_height: {$avg: {$toDecimal: '$height'}},
	avg_weight: {$avg: {$toDecimal: '$weight'}}
	}
},
{$project: {
	sex: '$_id',
	_id: 0,
	avg_height: '$avg_height',
	avg_weight: '$avg_weight'
	}
}
])
