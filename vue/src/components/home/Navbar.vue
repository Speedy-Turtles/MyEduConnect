<template>
    <div id="tab1">
        <v-app-bar app fixed class="align-center"  color="white" height="100" elevate-on-scroll scroll-target="#scrolling-techniques-7">
            <v-toolbar-title class="mt-5 ms-5">
                <img src="../../../public/images/logo-no-background.svg" alt="" width="150px" srcset="">
            </v-toolbar-title>

            <v-spacer></v-spacer>
            <router-link style="text-decoration: none;" to="signin">
            <v-btn outlined rounded color="#ffcf59" class="me-5">
                 SIGN IN 
            </v-btn>
             </router-link>
             <router-link style="text-decoration: none;" to="signup">
                <v-btn elevation="0" rounded color="#ffcf59" class="me-5" dark>
                    SIGN UP 
                </v-btn>
             </router-link> 
            <template v-slot:extension>
                <v-tabs centered slider-color="yellow">
                    <v-tab @click="click('tab1')">Tab 1</v-tab>
                    <v-tab @click="click('tab2')">Tab 2</v-tab>
                    <v-tab @click="click('tab3')">Tab 3</v-tab>
                </v-tabs>
            </template>
        </v-app-bar>
    </div>
</template>

<script>



export default {
    data(){
        return{
        
        }
    },
    methods:{
        click(a){
             document.getElementById(`${a}`)?.scrollIntoView({behavior: 'smooth'});
        }
    },
    components: {
    },
}
</script>