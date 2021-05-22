db.people.aggregate(
{$project:
	{height_dec:
		 {$toDecimal: '$height'}
	 }
},
{$match:
	{ height_dec:
		{ $gt: 190}
	 }
}
).forEach(doc =>
	{db.people.remove( {_id: doc._id} )}
)
