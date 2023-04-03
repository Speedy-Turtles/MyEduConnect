<template>
    <div class="navbar">
         <v-toolbar   class="toolbar px-5" >
            
            <a type="hidden" href=""></a>
            <!-- --------------------menu-------------------------- -->
            <v-menu offset-y >
                <template v-slot:activator="{ on, attrs }">
                    <v-btn
                    
                    v-bind="attrs"
                    v-on="on"
                    plain
                    class="hidden-md-and-up grey--text mr-3"
                    >
                    <v-icon size="43px" color="primary">
                        mdi-menu
                    </v-icon>
                    </v-btn>
                </template>
                <v-list>
                    
                    <v-list-item
                    v-for="link in links"
                    :key="link.titre"
                    >
                        <v-list-item-title>
                            <v-btn plain router :to="link.route">{{link.titre}}</v-btn>
                        </v-list-item-title>
                    </v-list-item>
                </v-list>
                </v-menu>
                <!-- --------------------menu/-------------------------- -->

                <!-- --------------------titre-------------------------- -->
                <v-toolbar-title class="mr-5 hidden-xs-only hidden-md-only">
<<<<<<< HEAD:vue/src/components/etudiant/Navbar.vue
                    
                    <img src="../../../public/etudiant/images/logo-no-background.svg" alt="" width="100px" srcset="">
=======
                    <img src="../../../../public/etudiant/images/logo-makeiteasy-no-background.svg" alt="" width="100px" srcset="">
>>>>>>> 4dd0444eaa83fc359fbfafaf5ed440c80bc1c8a3:vue/src/components/etudiant/layout/Navbar.vue
                </v-toolbar-title>
                <!-- --------------------titre/-------------------------- -->


                <!-- --------------------items-------------------------- -->
                <v-toolbar-items class="hidden-sm-and-down">

                    <!-- --------------------tooltips-------------------------- -->
                    <v-tooltip bottom v-for="link in links" :key="link.titre">
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn
                            plain
                            v-bind="attrs"
                            v-on="on"
                            id="btn-links"
                            class="black--text"
                            router :to="link.route"
                            >
                            <v-icon class="px-3">{{ link.icon }}</v-icon>
                            {{ link.titre }}
                            </v-btn>
                        </template>
                        <span>{{ link.desc  }}</span>
                    </v-tooltip>
                    
                     <!-- --------------------tooltips/-------------------------- -->
                </v-toolbar-items>
                <v-spacer></v-spacer>
                <v-menu offset-y>
                    <template v-slot:activator="{ on, attrs }">
                     <v-btn
                     plain
                     v-bind="attrs"
                     v-on="on"
                     
                     >
                        <v-icon size="35px">mdi-bell</v-icon>
                        <v-badge color="red" :content="messages"
                        :value="messages"></v-badge>
                     </v-btn>
                    </template>
                    <v-list>
                    <v-list-item
                    
                    >
                        <v-list-item-title>Notifications</v-list-item-title>
                        </v-list-item>
                    </v-list>
                </v-menu>

                <v-menu offset-y>
                    <template v-slot:activator="{ on, attrs }">
                    <v-btn   plain v-bind="attrs"
                     v-on="on"
                     >
                        <v-avatar size="43px">
<<<<<<< HEAD:vue/src/components/etudiant/Navbar.vue
                            <img :src="'http://localhost:8000'+store.user['Photo']" alt="" srcset="">
=======
                            <img src="../../../../public/etudiant/images/avatar.png" alt="" srcset="">
>>>>>>> 4dd0444eaa83fc359fbfafaf5ed440c80bc1c8a3:vue/src/components/etudiant/layout/Navbar.vue
                        </v-avatar>
                     </v-btn>
                    </template>
                    <v-list>
                    <v-list-item>
                        <v-list-item-title>
                            <v-btn
                                plain
                                block
                                class="py-3"
                            >
                            {{store.user['FirstName']}}
                            </v-btn>
                        </v-list-item-title>
                     </v-list-item>
                    <v-list-item>
                        
                        <v-list-item-action>
                            <v-btn
                                plain
                            >
                                <v-icon class="pa-2">mdi-wrench</v-icon>
                                <span class="">Settings</span>
                            </v-btn>
                        </v-list-item-action>
                        
                    </v-list-item>

                    <v-list-item>
                        
                        <v-list-item-action>
                            <v-btn
                                plain
                            >
                                <v-icon class="pa-2">mdi-shield-outline</v-icon>
                                <span class="">personal info </span>
                            </v-btn>
                        </v-list-item-action>
                        
                    </v-list-item>

                    <v-list-item>
                        
                        <v-list-item-action>
                            <v-btn
                                plain
                            >
                                <v-icon class="pa-2">mdi-logout</v-icon>
                                <span  @click="logout()">log out</span>
                            </v-btn>
                        </v-list-item-action>
                        
                    </v-list-item>
                    </v-list>
                </v-menu>
                     
                <!-- --------------------items/-------------------------- -->
        </v-toolbar> 
    </div>
</template>

<script>
import {AuthUser} from "@/store/Store.js"
    export default {
<<<<<<< HEAD:vue/src/components/etudiant/Navbar.vue
        setup(){
            const store=AuthUser();
            return{store}
        },
=======
        name:'Navbar',
>>>>>>> 4dd0444eaa83fc359fbfafaf5ed440c80bc1c8a3:vue/src/components/etudiant/layout/Navbar.vue
        data(){
            return{
                links:[
                    {titre:'home',link:'home',desc:'Home',icon:'mdi-home',route:'/etudiant'},
                    {titre:'Document',link:'document',desc:'Check Documents',icon:'mdi-table-edit',route:'/etudiant/document'},
                    {titre:'Forum',link:'forum',desc:'Go to Forum',icon:'mdi-comment-text-outline',route:'/etudiant/forum'},
                    {titre:'Club',link:'club',desc:'Enjoy Clubs',icon:'mdi-star-outline',route:'/etudiant/club'},
                    {titre:'Help',link:'help',desc:'How Can We help You !',icon:'mdi-wrench',route:'/etudiant/help'}
                ],
<<<<<<< HEAD:vue/src/components/etudiant/Navbar.vue
                messages:10
            }
        },
       methods:{
        logout(){
            this.store.logout();
            this.$router.push({name:'signin'});
        }
=======
                messages:10,
                
               
            }
        },
       methods:{
>>>>>>> 4dd0444eaa83fc359fbfafaf5ed440c80bc1c8a3:vue/src/components/etudiant/layout/Navbar.vue
       }
    }

</script>
<style scoped>
#demoFont {
font-family: "Montserrat", sans-serif;
font-size: 21px;
letter-spacing: 1.8px;
word-spacing: -1.6px;
font-weight: 700;
/* text-decoration: underline solid rgb(68, 68, 68); */
font-style: italic;
font-variant: small-caps;
text-transform: uppercase;
cursor: pointer;
}
.primary{
    color: #525fe1;
}
#btn-links{
font-family: "Times New Roman", Times, serif;
font-size: 21px;
letter-spacing: 1.8px;
word-spacing: -1.4px;
color: #525FE1;
font-weight: bolder;
font-style: italic;
font-variant: small-caps;
text-transform: uppercase;
}
.toolbar{
    position: fixed;
    z-index: 999;
    width: 100%;
   
}
</style>