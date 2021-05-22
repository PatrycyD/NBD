db.people.aggregate([
        {$project:
                {nationality: '$nationality',
                BMI: {$divide: ['$weight', {$multiply: [ {$divide: ['$height', 100] }, {$divide: ['$height', 100] } ] } ] }
                }
        },
        {$group:
                {_id: '$nationality',
                BMI_max: {$max: '$BMI'},
                BMI_min: {$min: '$BMI'},
                BMI_avg: {$avg: '$BMI'} }
        },
        {$project:
                {nationality: '$_id',
                _id: 0,
                BMI_max: {$round: ['$BMI_max', 2] },
                BMI_min: {$round: ['$BMI_min', 2] },
                BMI_avg: {$round: ['$BMI_avg', 2] }
                }
        },
        {$sort: {nationality: 1} } ] ).pretty()
