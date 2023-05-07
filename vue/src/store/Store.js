import { defineStore } from 'pinia'
import { ref,computed } from "vue"

export const AuthUser = defineStore('auth', ()=> {

    const token=ref(localStorage.getItem('token')??null);
    const user=ref(JSON.parse(localStorage.getItem('user')??null));
    const isAuth=ref(localStorage.getItem('token')&&localStorage.getItem('user'));
    const IsAdmin=ref(localStorage.getItem('IsAdmin')??false);
    const IsTechnicien=ref(localStorage.getItem('IsTechnicien')??false);
    const Isetudiant=ref(localStorage.getItem('Isetudiant')??false);
    const Ischef=ref(localStorage.getItem('Ischef')??false);
    const Isens=ref(localStorage.getItem('Isens')??false)

    const getUser=computed(()=>user.value);
    const gettoken=computed(()=>token.value);
    const getisauth=computed(()=>isAuth.value);
    const getIsadmin=computed(()=>IsAdmin.value);
    const getIsTechnicien=computed(()=>IsTechnicien.value);
    const getIsetudiant=computed(()=>Isetudiant.value);
    const getIschef=computed(()=>Ischef.value);
    const getIsens=computed(()=>Isens.value);


    function login(t,u,idadm,istech,isetud,ische,isens){
        user.value=u;
        token.value=t;
        isAuth.value=true;
        IsAdmin.value=idadm;
        IsTechnicien.value=istech;
        Ischef.value=ische;
        Isetudiant.value=isetud;
        Isens.value=isens;
        localStorage.setItem('token',t);
        localStorage.setItem('IsAdmin',idadm);
        localStorage.setItem('IsTechnicien',istech);
        localStorage.setItem('Ischef',ische);
        localStorage.setItem('Isetudiant',isetud);
        localStorage.setItem('Isens',isens);
        localStorage.setItem('user',JSON.stringify(u));
    }

    function logout(){
        user.value=null;
        token.value=null;
        isAuth.value=false;
        IsAdmin.value=false;
        IsTechnicien.value=false;
        Ischef.value=false;
        Isetudiant.value=false;
        Isens.value=false;
        localStorage.removeItem('token');
        localStorage.removeItem('IsAdmin');
        localStorage.removeItem('IsTechnicien');
        localStorage.removeItem('Ischef');
        localStorage.removeItem('Isetudiant');
        localStorage.removeItem('user');
        localStorage.removeItem('Isens');
    }

    function SetUser(u){
        user.value=null;
        localStorage.removeItem('user');
        user.value=u;
        localStorage.setItem('user',JSON.stringify(u));
    }

    return {
        token,user,isAuth,IsAdmin,IsTechnicien,Isetudiant,Ischef,Isens,
        getUser,gettoken,getisauth,getIsadmin,getIsTechnicien,getIsetudiant,getIschef,getIsens,
        login,logout,SetUser
    }
})