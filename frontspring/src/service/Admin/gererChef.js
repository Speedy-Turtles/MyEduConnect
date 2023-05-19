import Axios from "axios";

import "@/plugins/axios"

export default {
    getProffesors() {
        return Axios.get('/enseignats/all');
    },
    getChef() {
        return Axios.get('/getChef');
    },
    deleteChef(current_chef_id) {
        return Axios.delete('/deleteChef?user_id=' + current_chef_id);
    },
    changeChef(user_id) {
        return Axios.post('/changeChef?user_id=' + user_id);
    }
}