import { NuxtConfig } from "@nuxt/types";

const isProd = process.env.NODE_ENV !== 'production'

const config: NuxtConfig = {
    components: true,
    target: "static",
    build: {
        analyze: {
            analyzerMode: isProd ? "static" : "disabled"
        }
    }
}

export default config
