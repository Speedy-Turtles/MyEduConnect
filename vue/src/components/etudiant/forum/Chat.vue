<template>
    <div class="">
        <!-- <v-container>

            <div class="box">
                <div class="header text-center">
                   <h1>conversation</h1>
                </div>
                <div class="messages">
                    <div class="component py-5" v-for="chat in chats" :key="chat.id">
                        <div class="other" v-if="chat.id_envoi!=store.user['id']">
                            <v-layout wrap>
                                <v-flex>
                                    <v-avatar>
                                        <v-img :src="user[0].Photo"></v-img>
                                    </v-avatar>
                                    <span class="name ml-4">{{ user[0].FirstName }}<span class="time">{{chat.date}}</span></span>
                                </v-flex>
                            </v-layout>
                            <v-layout wrap>
                                    <v-flex class="mr-5">
                                        <div class="message-div mt-4">
                                            <span class="message">{{ chat.message }}</span>
                                        </div>
                                    </v-flex>
                            </v-layout>
                        </div>
                        <div class="mine float-end" v-else>
                            <v-layout wrap>
                                <v-flex>
                                    <v-avatar>
                                        <v-img :src="'http://localhost:8000'+store.user['Photo']" ></v-img>
                                    </v-avatar>
                                    <span class="name text-center ml-5">{{ store.user['FirstName'] }}<span class="time mr-4">{{chat.date}}</span></span>
                                </v-flex>
                            </v-layout>
                            <v-layout wrap>
                                    <v-flex class="">
                                        <div class="message-mine mt-4 float-end">
                                            <span class="message">{{ chat.message }}</span>
                                        </div>
                                    </v-flex>
                            </v-layout>
                        </div>               
                    </div> 
                </div>
            </div>
            <div class="send">
                    <form @submit.prevent="addMessage()" class="mt-2">
                        <v-container>
                            <v-layout row wrap>
                                <v-flex xl8 md8 lg8 sm6 xs12 class="mb-10 text-center">
                                    <input type="text" placeholder="write your message..." name="text" class="input" v-model="message">
                                </v-flex>
                                <v-flex xl4 md4 lg4 sm6 xs12 class="text-center">
                                        <v-btn class="button ml-5 pa-5" color="#525fe1" type="submit">Send</v-btn>   
                                </v-flex>
                            </v-layout>
                        </v-container>
                    </form> 
            </div>
        </v-container> -->

        
            <div class="loading text-center py-5 mt-5" v-if="loding_page==false">
                <v-progress-circular
                indeterminate
                color="primary"
                ></v-progress-circular>
            </div>
        <div class="container" v-else>
         <div class="chat">
             <div class="chat-header">
                    <div class="profil">
                        <div class="left">
                            <img src="../../../../public/etudiant/images/chat/phone.png" class="cs">
                            <h2>{{user[0].FirstName}}</h2>
                            <span>online</span>
                        </div>
                        <div class="right">
                            <img src="../../../../public/etudiant/images/chat/video.png" class="icon">
                            <img src="../../../../public/etudiant/images/chat/phone.png"  class="icon">
                            <img src="../../../../public/etudiant/images/chat/more.png"  class="icon">
                        </div>
                    </div>
             </div>
             <div class="chat-box" >

                <div class="chat-area">
                    <div class="chat"  v-for="chat in chats" :key="chat.id">
                        <div class="chat-l" v-if="chat.id_envoi!=store.user['id']">
                            <div class="mess">
                                <p>
                                    {{ chat.message }}
                                </p>
                                <div class="check">
                                        <span>{{ chat.date }}</span>       
                                </div>
                            </div>
                        </div>

                        <div class="chat-r" v-else>
                            <div class="sp"></div>
                            <div class="mess mess-r">
                                <p>
                                    {{ chat.message }}
                                </p>
                                <div class="check">
                                        <span>{{chat.date}}</span>
                                        <img src="../../../../public/etudiant/images/chat/check-2.png" >
                                </div>
                            </div>
                        </div>
                    </div>  
                </div>
                    
             </div>

             <form @submit.prevent="addMessage()" class="mt-2">
                <div class="chat-footer">
                    <textarea placeholder="type a message" v-model="message"></textarea>
                    <v-btn class="button ml-5 pa-5 white--text" color="#525fe1" type="submit">Send</v-btn> 
                </div>
            </form>
         </div>
     </div> 
    </div>
</template>
<script>
import { AuthUser } from '@/store/Store';
import forumService from '@/service/forum/forumService';
export default {
    mounted(){
        window.Echo.channel('chatRealTime').listen('chatlive',(e)=>{
               this.getChatById();
            }) 
    },
    setup(){
            const store=AuthUser();
            return{store}
        },
    created(){
        this.id = this.$route.params.id;
        console.log(this.id);
        this.getChatById();
        this.getUserById();
        console.log(this.store.user['id'])
    },

    data(){
        return{
            id:-1,
            chats:[],
            user:[],
            message:"",
            loding_page:false,
        }
    },
    methods:{
        getChatById(){
            forumService.getChatById(this.id).then((res=>{
            
                for(let i=0;i<(res.data.data).length;i++){
                     this.chats.push({id:res.data.data[i].id,id_envoi:res.data.data[i].id_envoi,id_recu:res.data.data[i].id_recu,message:res.data.data[i].message,date:res.data.data[i].created_at.substring(0,10)});
                 }
                 this.loding_page=true;
            })).catch((err=>{
                console.log(err);
            }))
        },
        getUserById(){
            forumService.getUseById(this.id).then((res=>{
                this.user=res.data.data
                console.log(this.user[0].FirstName);
            })).catch((err=>{
                console.log(err);
            }))
        },
        addMessage(){
            forumService.addMessage(this.id,{
                "message":this.message
            }).then((res=>{
                this.message="";
                console.log(res.data.data);
            })).catch((err=>{
                console.log(err);
            }))
        }
    }
}
</script>
<style scoped>
/* .box{
    background-color: aliceblue;
    height: auto;
    border-radius: 20px;
   
}
.no-message{
    color: #094fa4;
}
.messages{
    overflow-y: scroll;
    height: 100vh;
    scroll-margin-block-start: 40px;
    scrollbar-width: none;
    scrollbar-color: #12c2b9;
}
.send{
    margin-top: 200px;
    background-color: white;
}
.header{
    height: 100px;
    background-color: rgb(175, 206, 232);
    padding: 20px;
    border-radius: 20px;
    -webkit-box-shadow: -2px 9px 23px -3px #094fa4; 
    box-shadow: -2px 9px 23px -3px #6791c6;
}
.header>h1{
    font-family: "Times New Roman", Times, serif;
    font-size: 40px;
    letter-spacing: 1px;
    word-spacing: 2px;
    color: rgb(255, 255, 255);
    font-weight: 700;
    text-decoration: none;
    font-style: normal;
    font-variant: normal;
    text-transform: capitalize;

}
 
.message-div{
        border: solid #4e538d 2px;
        padding: 20px;
        margin-right: 20px;
        border-radius: 10px;
        box-sizing:border-box;
        max-width: 85%;
        background-color: #cfd0e7;
        display: table;
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
    .message{
        text-align: justify;
        font-size: 15px;
        display: table-cell;
    }
    .name{
        font-size: 15px;
        font-weight: bolder;
        text-transform: capitalize;
        
    }
    .time{
        font-size: 10px;
        margin-left: 5px;
        color: rgb(135, 133, 133);
        font-weight: lighter;
    }
    .component{
        padding: 50px;
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
    .other{
        margin-bottom: 50px;
    }
    .mine{
        text-align: end;
    } */





.loading{
    height: 100vh;
}




*{
    padding: 0;
    margin: 0;
    box-sizing: border-box;
    font-family: 'Times New Roman', Times, serif;
}
.container{
    width: 500px;
    margin: auto;
}
.chat{
    display: flex;
    flex-direction: column;
    background: white;
}
.chat-header{
    display: flex;
}
.profil{
    width: 100%;
    background: rgb(77, 96, 192);
    display: flex;
    align-items: center;
    height: 60px;
    padding: 0px 10px;
    position: relative;
}
.profil .cs{
    display: block;
    width: 40px;
    position: absolute;
    top: 12px;
    cursor: pointer;
    border-radius: 20px;
}
.profil h2{
    display: inline-block;
    line-height: 90px;
    vertical-align: bottom;
    color: #fff;
    margin-left: 50px;
    font-size: 20px;
    font-weight: 600;
}
.profil span{
    color: #ccc;
    position: absolute;
    top: 42px;
    left: 70px;
    font-size: 14px;
}
.profil .left{
    flex: 1;
}
.right .icon{
    display: inline-block;
    width: 25px;
    margin-left: 8px;
}
.chat-area{
    overflow-y: scroll;
    height:70vh;
}
.chat-box{
    
    flex: 1;
    /* background-image: url(../../../../public/etudiant/images/chat/bg.jpeg); */
   
    padding-left: 20px;
}
.chat-footer{
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 60px;
    position: relative;
    cursor: pointer;
}
.chat-footer textarea{
    display: block;
    flex:1;
    width: 100%;
    border-radius: 60px;
    margin: 5px;
    height: 50px;
    outline: none;
    font-size: 19px;
    padding: 10px;
    padding-left: 40px;
    padding-right:90px ;
    border: 2px solid #ccc;
    color: #555;
    resize: none;
}
.chat-footer .mic{
    display: block;
    width: 45px;
    height: 45px;
    margin-top: -3px;
    margin-right: 18px;
}
.chat-footer .emo{
    display: block;
    width: 35px;
    height: 35px;
    position: absolute;
    left: 10px;
    top: 12px;
}
.chat-footer .icons{
    position: absolute;
    right: 100px;
    top: 10px;
}
.chat-footer .icons img{
    display: inline-block;
    width: 35px;
    height: 35px;
}
.chat-r{
    display: flex;
}
.chat-r .sp{
    flex: 1;
}
.chat-l{
    display: flex;
}
.chat-l .sp{
    flex: 1;
}
.chat-box .mess{
    max-width: 300px;
    background-color: #f7fcf6;
    padding: 10px;
    border-radius: 10px;
    margin: 20px 0px;
    cursor: pointer;
}
.chat-box .mess p{
    word-break: break-all;
    font-size: 18px;
}
.chat-box .mess-r{
    background: #e2ffc7;
}
.chat-box .emoji{
    width: 20px; 
}
.chat-box .check{
    float: right;
}
.chat-box .check img{
    width: 20px;
}
.chat-box .check span{
    color: #888;
    font-size: 12px;
    font-weight: 700px;
}
*::-webkit-scrollbar{
    width: 5px;
}
*::-webkit-scrollbar-track{
    background-color: #f1f1f1;
}
*::-webkit-scrollbar-thumb{
    background-color: #aaa;
}
*::-webkit-scrollbar-thumb:hover{
    background-color: #555;
}
.chat-box .img_chat{
    width: 280px;
}
@media screen and (max-width:400px){
    .container{
        width: 376px;
    }
}
</style>