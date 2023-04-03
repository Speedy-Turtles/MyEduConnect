import axios from "axios";
import "@/plugins/axios";
import { AuthUser } from "@/store/Store.js";

export default {

    StoreUser(user) {
        let data = new FormData();
        data.append("firstname", user.firstname);
        data.append("lastname", user.lastname);
        data.append("email", user.email);
        data.append("birth_day", user.birthday);
        data.append("password", user.password);
        data.append("Role_name", user.Role_name);
        data.append("photo", user.photo);
        data.append("sex", user.sex);
        data.append("cin", user.cin);
        data.append("num_tlf", user.num_tlf);
        data.append("classe_id", user.classe_id);
        const config = {
            Headers: {
                "content-type": "multipart/form-data",
            },
        };
        console.log(user);
        return axios.post("auth/signup", data, config)
    },

    async login(email, password) {
        const store = AuthUser();
        const res = await axios.post("auth/login", { email, password });
        const data = res.data.data;
        // console.log(data);
        if (res.status == 200) {
            store.login(data.token, data.user, data.Isadmin,
                data.Istechnicien, data.Isetudiant, data.IsChefDepartement
            );
        } else {
            store.logout();
        }
    },

    RenvoyerEmail(email) {
        return axios.get("auth/RenvoyerLink/" + email);
    },
    VerifyEmail(email) {
        return axios.get("auth/verifyEmail/" + email);
    },
    forgotPassword(email) {
        return axios.post("auth/forgotPassword/" + email);
    },
    changerPassword(data) {
        return axios.post("auth/ChangerPassword", data);
    }

}