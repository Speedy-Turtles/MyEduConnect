import axios from "axios";
import "@/plugins/axios";

export default {
    getAcceptedEtudiants() {
        return axios.get("proffesors/students/accepted");
    },
    getPendingRequests() {
        return axios.get("proffesors/students/pending");
    }
}