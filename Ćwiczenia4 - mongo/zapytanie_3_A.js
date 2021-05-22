db.people.aggregate({$group: {_id: '$job'} }, {$project: {job: '$_id', _id: 0} }, {$sort: {job: 1} } )
