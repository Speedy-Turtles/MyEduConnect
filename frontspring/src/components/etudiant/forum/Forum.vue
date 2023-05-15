<template>
<div class="forum">
    <v-container fluid>
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
                        <v-list-item link>
                            <v-list-item-avatar>
                                <v-img src="../../../assets/ghassen.jpg"></v-img>
                            </v-list-item-avatar>
                            <v-list-item-title> Ghassen Ahmed (Role)</v-list-item-title>
                        </v-list-item>
                        <v-list-item link>
                            <v-list-item-avatar>
                                <v-img src="../../../assets/ghassen.jpg"></v-img>
                            </v-list-item-avatar>
                            <v-list-item-title> Ghassen Ahmed (Role)</v-list-item-title>
                        </v-list-item>
                        <v-list-item link>
                            <v-list-item-avatar>
                                <v-img src="../../../assets/ghassen.jpg"></v-img>
                            </v-list-item-avatar>
                            <v-list-item-title> Ghassen Ahmed (Role)</v-list-item-title>
                        </v-list-item>
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
                                <div class="component py-5" v-for="i in 4">
                                    <v-layout wrap>
                                    <v-flex>
                                        <v-avatar>
                                            <v-img src="../../../assets/ghassen.jpg"></v-img>
                                        </v-avatar>
                                        <span class="name ml-4">Ghassen Ahmed <span class="time"> 04-06-2023</span></span>
                                    </v-flex>
                                    </v-layout>
                                    <v-layout wrap>
                                        <v-flex class="mr-5">
                                            <div class="message-div mt-4">
                                                <span class="message">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Ipsam dicta recusandae excepturi maxime rerum iste harum. Repudiandae, perspiciatis? Odit repellat omnis dicta nisi et nam quisquam accusantium ducimus voluptas consequatur?</span>
                                            </div>
                                        </v-flex>
                                    </v-layout>
                                </div>  
                            </div>
                            
                        </v-flex>
                    </v-layout>
                    
                        <v-layout row>
                            <v-flex>
                                <div class="container-input">
                                    <v-container>
                                        <v-layout row wrap>
                                            <v-flex xl8 md8 lg8 sm6 xs12 class="mb-10">
                                                <input type="text" placeholder="write your message..." name="text" class="input">
                                            </v-flex>
                                            <v-flex xl4 md4 lg4 sm6 xs12>
                                                    <v-btn class="button ml-5 pa-5" color="#10706d">Post</v-btn>   
                                            </v-flex>
                                            
                                        </v-layout>
                                    </v-container>
                                    
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
export default {

}
</script>
<style scoped>
.button{
    color: white;
    border-radius: 10px;
    width: 150px;
}
.title{
    margin-top: 10px;
    color: #12c2b9;
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
    border: solid #12c2b9 2px;
    padding: 20px;
    margin-right: 20px;
    border-radius: 10px;
    box-sizing:border-box;
    max-width: 85%;
    background-color: #dbfffe;
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

.container-input {
  margin-top: 200px;
}

.input {
  width: 300px;
  padding: 10px 0px 10px 40px;
  border-radius: 9999px;
  border: solid 1px #12c2b9;
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