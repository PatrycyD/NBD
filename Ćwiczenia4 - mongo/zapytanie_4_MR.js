var mapFunc = function(){
        var BMI = this.weight / ( (this.height/100) * (this.height/100) )
        emit(this.nationality, {BMI: BMI, count: 1});
};

var reduceFunc = function(nationality, values){
        var BMIs = {BMI_max: 0, BMI_min: 0, BMI_total: 0, BMI_count: 0}

        for (i = 0; i < values.length; i++){
                BMIs.BMI_total += values[i].BMI;
                BMIs.BMI_count += values[i].count;
                if (values[i].BMI > BMIs.BMI_max){
                        BMIs.BMI_max = values[i].BMI;
                };
                if (values[i].BMI < BMIs.BMI_min){
                        BMIs.BMI_min = values[i].BMI;
                } else if (BMIs.BMI_min == 0) {
			BMIs.BMI_min = values[i].BMI
		};
        };
return BMIs};

var finalizeFunc = function(nationality, values){
	values.average = values.BMI_total / values.BMI_count;
	return {BMI_max: values.BMI_max, BMI_min: values.BMI_min, BMI_avg: values.average};
};

db.people.mapReduce(
mapFunc,
reduceFunc,
{finalize: finalizeFunc,
out: 'MapReduce_4'}
)

db.MapReduce_4.aggregate([
	{$unwind: '$value'},
	{$project:
		{nationality: '$_id',
		_id: 0,
		BMI_max: {$round: ['$value.BMI_max', 2] },
		BMI_min: {$round: ['$value.BMI_min', 2] },
		BMI_avg: {$round: ['$value.BMI_avg', 2] }, }
	},
	{$sort: {nationality: 1} }
]).pretty()
