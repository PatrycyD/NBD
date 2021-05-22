var mapFunc = function(){
emit (this.job, 1);
};

var reduceFunc = function(job){
return [new Set(job)];
}

db.people.mapReduce(
mapFunc,
reduceFunc,
{out: 'MapReduce_3'}
)

db.MapReduce_3.aggregate({$project: {job: '$_id', _id: 0} }, {$sort: {job: 1} }).pretty()
