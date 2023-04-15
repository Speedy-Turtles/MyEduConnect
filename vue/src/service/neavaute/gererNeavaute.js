import axios from "axios";
import "@/plugins/axios";

export default {
    getNeavaute() {
        return axios.get('/nouveaute/Allnouveaute');
    },
    addNeavaute(nouveaute) {
        return axios.post('/nouveaute/addnouveaute', nouveaute);
    },
    DeleteNeauv(id) {
        return axios.delete('/nouveaute/deletenouveaute/' + id);
    },
    getNeavauteById(id) {
        return axios.get('/nouveaute/getnouveauteById/' + id);
    },
    updateNeavaute(id, nouveaute) {
        return axios.put('/nouveaute/updatenouveaute/' + id, nouveaute);
    },
    searchNeavaute(search) {
        return axios.get('/nouveaute/Allnouveaute?search=' + search);
    }
}