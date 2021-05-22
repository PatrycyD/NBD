var mapFunc = function(){ 
	emit(this.sex, {count: 1, height: this.height, weight: this.weight}); 
};

var reduceFunc = function(sex, values){
	var value = {count: 0, height: 0, weight: 0}; 

	for (i = 0; i < values.length; i++){
		value.count += values[i].count; 
		value.height += values[i].height; 
		value.weight += values[i].weight; 
} 
return value; 
};

var finalizeFunc = function(sex, value){ 
	var averages = {average_height: 0, average_weight: 0}
	averages.average_height = (value.height / value.count); 
	averages.average_weight = (value.weight / value.count); 
	return averages; 
};

db.people.mapReduce(
mapFunc,
reduceFunc,
{out: 'MapReduce_1',
finalize: finalizeFunc
}
);

db.MapReduce_1.find().pretty()

