
<template>
    <v-footer color="#525fe1" class="white--text text-center mb-0">
        <v-row>
            <v-col cols="3">
                <v-toolbar-title class="ms-5">
                    <img src="../../../public/images/logo-no-background-white.svg" alt="" width="150px" srcset="">
                </v-toolbar-title>
            </v-col>
            <v-col cols="3">
                <p class="font-weight-bold display-5">
                    Useful Links
                </p>
            </v-col>
            <v-col cols="3">
                <p class="font-weight-bold display-5">
                    Resources
                </p>
            </v-col>
            <v-col cols="3"> <v-btn
          v-for="icon in icons"
          :key="icon"
          class="mx-4 white--text"
          icon
        >
          <v-icon size="24px">
            {{ icon }}
          </v-icon>
        </v-btn></v-col>

            <br />

            <v-col>{{ new Date().getFullYear() }} — <strong>MYEDUCONNECT</strong> </v-col> </v-row>
    </v-footer>
</template>
<script>
export default {
    data: () => ({
        icons: [
            'mdi-facebook',
            'mdi-twitter',
            'mdi-linkedin',
            'mdi-instagram',
        ],
    }),
}
</script>
