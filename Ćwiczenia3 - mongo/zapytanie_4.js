db.people.aggregate([{$project: {all: '$$ROOT', weight: {$toDecimal: '$weight'} } }, {$match: {weight: {$gte: 68, $lt: 71.5} } }, {$project: {_id: 0, weight: 0} }]).pretty()
