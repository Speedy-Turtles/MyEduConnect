<template>
    <div>
        <v-app-bar app fixed class="align-center"  color="white" height="100" elevate-on-scroll scroll-target="#scrolling-techniques-7">
            <v-toolbar-title class="mt-5 ms-5">
                <img src="../../../public/images/logo-no-background.svg" alt="" width="150px" srcset="">
            </v-toolbar-title>

            <v-spacer></v-spacer>

            <v-btn outlined rounded color="#ffcf59" class="me-5">
                SIGN IN
            </v-btn>

            <v-btn elevation="0" rounded color="#ffcf59" class="me-5" dark>
                SIGN UP
            </v-btn>
            <template v-slot:extension>
                <v-tabs centered slider-color="yellow">
                    <v-tab>Tab 1</v-tab>
                    <v-tab>Tab 2</v-tab>
                    <v-tab>Tab 3</v-tab>
                </v-tabs>
            </template>
        </v-app-bar>
    </div>
</template>

<script>

export default {
    data: () => ({
        //

    }),
    components: {
    },
}
</script>