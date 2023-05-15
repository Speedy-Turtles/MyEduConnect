import axios from "axios";
import "@/plugins/axios";

export default {
    getAcceptedProffesors() {
        return axios.get("proffesors/accepted");
    },
    getPendingRequests() {
        return axios.get("proffesors/pending");
    },
    getEmploi() {
        return axios.get("/emploi");
    }
}