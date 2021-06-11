import riak

riak_client = riak.RiakClient(pb_port=8087, protocol='pbc')
bucket = riak_client.bucket('s22190')

root_url = "http://localhost:8087/buckets/22190/keys/{}"
headers = {'Content-Type':'application/json'}

# Czesc 1 - insert do bazy

new_doc_json = {"name": "Maria", "surname": "Marianska", "job_title": "teacher", "salary": 3000}
new_obj = bucket.new('worker6', new_doc_json)
new_obj.store()

# Czesc 2 - pobranie i wypisanie

result = bucket.get('worker6')
print(result)
print(f'\nWynik po pierwszym insercie: {result.encoded_data}')

# Czesc 3 - modyfikacja i ponowne wypisanie

result.data["job_title"] = "programmer"
result.data["salary"] = 15000

result.store()

result2 = bucket.get('worker6')
print(f'\nWynik po modyfikacji: {result2.encoded_data}')

# Czesc 4 - usuniecie rekordu

result2.delete()
result3 = bucket.get('worker6')
print(f'\nWynik po usunieciu rekordu: {result3.encoded_data}')