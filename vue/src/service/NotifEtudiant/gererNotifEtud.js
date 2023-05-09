import axios from "axios";
import "@/plugins/axios";
export default {
    getNotifEtud() {
        return axios.get('/notif');
    },
    getNotifNotSeen() {
        return axios.get('/notifNotSeen');
    },
    updateNotif() {
        return axios.post('/editnotif');
    },
    deleteAllNotif() {
        return axios.delete('/deleteAllNotif');
    }

}