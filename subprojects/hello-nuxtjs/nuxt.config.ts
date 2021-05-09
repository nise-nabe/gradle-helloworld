import { NuxtConfig } from "@nuxt/types";

const config: NuxtConfig = {
    target: "static",
    dev: process.env.NODE_ENV !== 'production',
}

export default config
