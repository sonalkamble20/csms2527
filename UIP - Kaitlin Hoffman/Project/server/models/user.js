//1. import mongoose
const mongoose = require("mongoose");

//2. create schema for entity
const userSchema = new mongoose.Schema({
    username: {type: String, unique: true, required: true},
    password: {type: String, required: true},
    followers: [String],
    following: [String]
})

//3. create model of schema
const User = mongoose.model("User", userSchema);

//4. create CRUD functions on model

//create

async function register(username, password) {
    const user = await getUser(username);

    if(user) throw Error('Username already in use');

    const newUser = await User.create({
        username: username,
        password: password
    });

    return newUser;
}

//read
async function login(username, password) {
    const user = await getUser(username);

    if(!user) throw Error('User not found');
    if(user.password != password) throw Error('Wrong Password');

    return user;
}

//update
async function updatePassword(id, password) {
    const user = await User.updateOne({"_id": id}, {$set: {password: password}});
    return user;
}

//delete
async function deleteUser(id) {
    return await User.findOne({ "username": username});
}

//5. eporting functions to routes

module.exports = {
    register, login, updatePassword, deleteUser
}