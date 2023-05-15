<template>
    <div class="forum">

        <div class="loading text-center py-5 mt-5" v-if="loding_page==false">
                <v-progress-circular
                indeterminate
                color="primary"
                ></v-progress-circular>
        </div>
        <v-container fluid v-else>
            <!-- <v-layout row >
                <v-flex>
                    <div class="title d-flex align-center justify-center">
                        <h3>Online real-time group text chat</h3>
                    </div>
                </v-flex>
            </v-layout> -->
            <v-layout  row wrap class="mt-10">
                <v-flex xl2 lg2 md2 sm12 xs12 class="hidden-sm-and-down">
                    <div class="users">
                        <v-navigation-drawer
                            permanent
                        >
    
                            <v-list>
                            <v-list-item class="px-10">
                               <v-list-item-title>
                                Users
                               </v-list-item-title>
                            </v-list-item>
                            </v-list>
                            <v-divider></v-divider>
    
                            <v-list
                            nav
                            dense
                            >
                            <div class="list-users">
                                
                                <v-list-item link v-for="user in users" :key="user.id">
                                    <router-link :to="`/etudiant/chat/${user.id}`">
                                        <v-list-item-avatar>
                                            <v-img :src="user.Photo"></v-img>
                                        </v-list-item-avatar>
                                        <v-list-item-title>{{ user.FirstName }}</v-list-item-title>
                                        <v-list-item-subtitle>({{ user.roles[0].Role_name }})</v-list-item-subtitle>
                                    </router-link>
                                </v-list-item>
                            </div>
                            
                            </v-list>
                        </v-navigation-drawer>
                    </div>
                </v-flex>
    
                <v-flex xl10 lg10 md10 sm12 xs12 class="room">
                    <v-layout row>
                        <v-flex>
                            <div class="title d-flex align-center justify-center">
                                <h3>Online real-time group text chat</h3>
                            </div>
                            <v-divider class="title-divider"></v-divider>
                        </v-flex>
                    </v-layout>
                    <v-container class="ma-15">
                        <v-layout row wrap>
                            <v-flex> 
                                <div class="scroll">
                                    <v-container>
                                        <div class="component py-5" v-for="forum in forums" :key="forum.id">
                                        <div class="other" v-if="forum.id!=store.user['id']">
                                            <v-layout wrap>
                                                <v-flex>
                                                    <v-avatar>
                                                        <v-img :src="forum.photo"></v-img>
                                                    </v-avatar>
                                                    <span class="name ml-4">{{forum.nom}}<span class="time"> {{forum.date}}</span></span>
                                                </v-flex>
                                             </v-layout>
                                             <v-layout wrap>
                                                    <v-flex class="mr-5">
                                                        <div class="message-div mt-4">
                                                            <span class="message">{{ forum.post }}</span>
                                                        </div>
                                                    </v-flex>
                                            </v-layout>
                                        </div>
                                        <div class="mine float-end mr-5 py-5" v-else>
                                            <v-layout wrap>
                                                <v-flex>
                                                    <v-avatar>
                                                        <v-img :src="'http://localhost:8000'+store.user['Photo']"></v-img>
                                                    </v-avatar>
                                                    <span class="name ml-5">{{ forum.nom }}<span class="time mr-4"></span></span>
                                                </v-flex>
                                            </v-layout>
                                            <v-layout wrap>
                                                    <v-flex class="">
                                                        <div class="message-mine mt-4 float-end">
                                                            <span class="message">{{ forum.post }}</span>
                                                        </div>
                                                    </v-flex>
                                            </v-layout>
                                        </div>   
                                        
                                    </div>  
                                    </v-container>
                                    
                                </div>
                                
                            </v-flex>
                        </v-layout>
                        
                            <v-layout row>
                                <v-flex>
                                    <div class="container-input">
                                        
                                        <form @submit.prevent="addPost()" class="mt-10">
                                            <v-container>
                                            <v-layout row wrap>
                                                <v-flex xl8 md8 lg8 sm6 xs12 class="mb-10">
                                                    <input type="text" placeholder="write your message..." name="text" class="input" v-model="post">
                                                </v-flex>
                                                <v-flex xl4 md4 lg4 sm6 xs12>
                                                        <v-btn class="button ml-5 pa-5" color="#525fe1" type="submit">Post</v-btn>   
                                                </v-flex>
                                            </v-layout>
                                            </v-container>
                                        </form>                                              
                                    </div>
                                </v-flex >
                            </v-layout>
                    </v-container>
                </v-flex>
            </v-layout>
        </v-container>
    </div>
    </template>
    <script>
    import forumService from "@/service/forum/forumService.js";
    import {AuthUser} from "@/store/Store.js";
   
    export default {
        created(){
            this.getForums();
            this.getAllUsers();
        },
         setup(){
        const store=AuthUser();
        return{store}
      },
        data(){
            return{
                forums:[],
                post:"",
                users:[],
                loding_page:false,
            }
        },
        methods:{
            getForums(){
                forumService.getForums().then((res)=>{
                    console.log(res.data.data);
                    for(let i=0;i<(res.data.data)?.length;i++){
                        this.forums.push({id:res.data.data[i].id,nom:res.data.data[i].FirstName,post:res.data.data[i].post,photo:res.data.data[i].Photo,date:(res.data.data[i].created_at).substring(0,10)})
                    }
                    this.loding_page=true;
                    console.log(this.forums);
                }).catch((err)=>{
                    console.log(err);
                })
            },
            addPost(){
                forumService.addPost({
                    "post":this.post
                }).then((res=>{
                    this.post="";
                })).catch((err=>{
                    console.log(err);
                }))
            },
            getAllUsers(){
                
                forumService.getAllUsers().then((res=>{
                    this.users=res.data.data;
                    console.log(this.users[0].id);
                })).catch((err=>{
                    console.log(err);
                }))
            }

        }
    
    }
    </script>
    <style scoped>
    .loading{
    height: 100vh;
    }
    .button{
        color: white;
        border-radius: 10px;
        width: 150px;
    }
    .message-mine{
        border: solid #4e538d 2px;
        padding: 20px;
        margin-right: 20px;
        border-radius: 10px;
        box-sizing:border-box;
        max-width: 85%;
        background-color: #ffffff;
        text-align: justify;
        display: inline-block;
        display: table;
}
    .title{
        margin-top: 10px;
        color: #525fe1;
        width: auto;
    }
    .title-divider{
        margin-top: 34px;
    }
    
    .users{
        height: 100vh;
    }
    
    .v-navigation-drawer__content{
        width: 100% !important;
    }
    
    .message-div{
        border: solid #525fe1 2px;
        padding: 20px;
        margin-right: 20px;
        border-radius: 10px;
        box-sizing:border-box;
        max-width: 85%;
        background-color: #f0f1ff;
    }
    .message{
        text-align: justify;
        font-size: 15px;
    }
    .name{
        font-size: 15px;
        font-weight: bolder;
        
    }
    .time{
        font-size: 10px;
        margin-left: 5px;
        color: rgb(135, 133, 133);
        font-weight: lighter;
    }
    *{
        overflow: hidden;
    }
    
    .scroll{
        overflow-y: scroll;
        height: 70vh;
        scroll-margin-block-start: 40px;
        scrollbar-width: none;
        scrollbar-color: #12c2b9;
    }
    .list-users{
        overflow-y: scroll;
        height: 70vh;
        scroll-margin-block-start: 40px;
        scrollbar-width: none;
        scrollbar-color: #12c2b9;
    }
    
    .container-input {
      margin-top: 200px;
    }
    
    .input {
      width: 300px;
      padding: 10px 0px 10px 40px;
      border-radius: 9999px;
      border: solid 1px #525fe1;
      transition: all .2s ease-in-out;
      outline: none;
      opacity: 0.8;
     
    }
    .input::placeholder{
       color: #0d5855;
    }
    
    .container-input svg {
      position: absolute;
      top: 50%;
      left: 10px;
      transform: translate(0, -50%);
    }
    
    .input:focus {
      opacity: 1;
      width: 350px;
    }
    
    </style>