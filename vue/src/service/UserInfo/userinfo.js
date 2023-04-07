import axios from "axios";
import "@/plugins/axios";

export default {

    TestExistEmail(email) {
        return axios.get("auth/Existmail/" + email);
    },
    TestExistcode(code) {
        return axios.get("auth/testExistToken/" + code);
    },
    GetNiveau() {
        return axios.get("/getNiveau");
    },
    ActiveUser(id, status) {
        return axios.post('/active/' + id, status);
    }
}