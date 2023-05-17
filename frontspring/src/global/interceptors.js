import Axios from "axios";
import { AuthUser } from "../store/AuthStore.js";

export function interceptors() {
  Axios.interceptors.request.use(
    function (config) {
      const store = AuthUser();
      config.headers.Authorization = `Bearer ${store.token}`;
      return config;
    },
    function (error) {
      return Promise.reject(error);
    }
  );
}