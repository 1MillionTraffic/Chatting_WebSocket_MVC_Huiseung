db = db.getSiblingDB('mydb');
db.createUser({
    user:'rootusername',
    pwd:'rootpassword',
    roles: [{
        role: 'readWrite',
        db: 'mydb'
        }
    ]}
);
