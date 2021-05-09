import { NuxtConfig } from "@nuxt/types";

const config: NuxtConfig = {
    dev: process.env.NODE_ENV !== 'production',
}

export default config
