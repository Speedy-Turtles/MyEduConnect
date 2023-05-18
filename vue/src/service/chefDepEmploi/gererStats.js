import axios from "axios";
import "@/plugins/axios";
export default {
    getStats() {
        return axios.get("/chefDep");
    }
}