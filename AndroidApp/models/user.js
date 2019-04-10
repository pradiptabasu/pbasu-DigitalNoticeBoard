var mongoose = require('mongoose')
var bcrypt = require('bcrypt-nodejs');

var schema = mongoose.Schema;

var userSchema = new schema({
    username:{
        type:String,
        required:true,
    },
    password: {
        type: String,
        required: true,
    },
    firstname: {
        type: String,
        required: false,
    },
    lastname: {
        type: String,
        required: false,
    },
    role: {
        type: String,
        enum: ['Admin', 'Teacher', 'Student'],
        required: false,
    },
})

userSchema.methods.hashPassword = function (password) {
    return bcrypt.hashSync(password,bcrypt.genSaltSync(10))
}

userSchema.methods.comparePassword = function (password,hash) {
    return bcrypt.compareSync(password,hash)
}

module.exports = mongoose.model('users',userSchema,'users');