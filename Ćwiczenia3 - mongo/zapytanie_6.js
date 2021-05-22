db.people.insert({sex: 'Male',
first_name: 'Patrycy',
last_name: 'Dworakowski',
job: 'Data Analyst',
email: 'patrycy.dworakowski@pjwstk.edu.pl',
location: {
	city: 'Warsaw',
	 address: {
		streetname: 'Koszykowa',
		streetnumber: 86} },
description: 'Brak opisu',
height: 100,
weight: 100,
birth_date: ISODate('2021-04-20'),
nationality: 'Poland',
credit: [
	{type: 'laser', number: 'XXXYYYZZZAAABBBCCC', currency: 'PLN', balance: 0},
	{type: 'bankcard', number: 111222333444555, currency: 'DOGE', balance: 20}]
})
