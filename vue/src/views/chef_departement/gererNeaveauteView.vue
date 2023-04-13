<template>
    <div>
        <navbar />
        <navigationDrawer current-page="neauveuate" />
        <div class="main_content">
            <v-tabs>
                <v-tab>
                    <v-btn text>view neauvtly<v-icon>mdi-view-module</v-icon></v-btn>
                </v-tab>
                <v-tab>
                    <v-btn text>add a new neauvtly<v-icon>mdi-plus</v-icon></v-btn>
                </v-tab>
            </v-tabs>
            <v-container style="margin-top: 3%!important;">
                <v-row>
                    <v-col>
                        <v-card>
                            <v-row>
                                <v-col md="6">
                                    <v-img src="../../../public/etudiant/images/logoiset.png" height="50%" width="100%"
                                        contain></v-img>
                                </v-col>
                                <v-col md="6">
                                    <v-row>
                                        <v-col>
                                            <v-card-title>neavaute titre</v-card-title>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col>
                                            <v-card-text>
                                                Lorem ipsum dolor sit amet consectetur adipisicing elit. Mollitia, vel dicta
                                                asperiores aut
                                                laboriosam beatae molestiae, magnam nostrum rem similique, consequatur
                                                libero. Delectus sit nemo
                                            </v-card-text>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col justify="end" align="right">
                                            <v-card-actions>
                                                <v-btn  color="orange" @click="tkalem()"><v-icon>mdi-pen</v-icon></v-btn>
                                                <v-btn  color="red"><v-icon>mdi-delete</v-icon></v-btn>
                                            </v-card-actions>
                                        </v-col>
                                    </v-row>
                                </v-col>
                            </v-row>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
        </div>
    </div>
</template>
  
  
<script>
import navbar from '@/components/chef_departement/navbar.vue';
import navigationDrawer from '@/components/chef_departement/navigationDrawer.vue';

export default {
    components: {
        navbar,
        navigationDrawer
    },
    created(){
        this.tkalem("welcome in my edu connect");
    },
    methods:{
        tkalem(text){
            let test=new SpeechSynthesisUtterance(text);
            speechSynthesis.speak(test);
        }
    }
}
</script>
<style scoped>.main_content {
    margin-left: 20%;
    margin-top: 5%;
}</style>