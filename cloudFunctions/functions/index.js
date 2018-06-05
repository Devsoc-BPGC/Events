const functions = require('firebase-functions');
const admin = require("firebase-admin");
let db;

admin.initializeApp(functions.config().firebase);
db = admin.database();

exports.events = functions.https.onRequest((request, response) => {

});


// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
exports.helloWorld = functions.https.onRequest((request, response) => {
    response.send("Hello from Firebase!");
});