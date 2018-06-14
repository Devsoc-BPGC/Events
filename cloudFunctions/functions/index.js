const functions = require('firebase-functions');
const admin = require("firebase-admin");
let db;

admin.initializeApp(functions.config().firebase);
db = admin.database();

exports.notifyAboutAppUpdate = functions.database.ref('/aboutfest')
    .onUpdate((snapshot, context) => {
        const newData = snapshot.after.val();
        const msg = {
            data: {
                type: 'aboutAppUpdate',
                value: JSON.stringify(newData)
            },
            topic: 'aboutAppUpdate'
        };
        return admin.messaging().send(msg).then((response) => {
            console.log('Successfully sent about app update:', response);
            return 0;
        }).catch((error) => {
            console.log('Error sending about app update:', error);
            throw error;
        });
    });
