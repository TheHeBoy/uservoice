import vue from '@vitejs/plugin-vue';
import vueJsx from '@vitejs/plugin-vue-jsx';
import svgLoader from 'vite-svg-loader';
import AutoImport from 'unplugin-auto-import/vite';
import Components from 'unplugin-vue-components/vite';
import Icons from 'unplugin-icons/vite';
import IconsResolver from 'unplugin-icons/resolver';
import { ElementPlusResolver, VueUseComponentsResolver } from 'unplugin-vue-components/resolvers';
import WindiCSS from 'vite-plugin-windicss';
import ViteFonts from 'vite-plugin-fonts';
import VueI18n from '@intlify/vite-plugin-vue-i18n';
import { ConfigEnv } from 'vite';
import { resolve } from 'path';

export default (env: ConfigEnv) => {
  return [
    vue({
      include: [/\.vue$/],
    }),
    vueJsx(),
    svgLoader(),
    AutoImport({
      presetOverriding: true,
      dts: './src/auto-imports.d.ts',
      imports: ['vue', 'pinia', 'vue-router', 'vue-i18n', '@vueuse/core'],
      eslintrc: {
        enabled: false, // Default `false`
        filepath: './.eslintrc-auto-import.json', // Default `./.eslintrc-auto-import.json`
        globalsPropValue: true, // Default `true`, (true | false | 'readonly' | 'readable' | 'writable' | 'writeable')
      },
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      dts: './src/components.d.ts',
      extensions: ['vue', 'md'],
      include: [/\.vue$/, /\.vue\?vue/, /\.md$/],
      // imports 指定组件所在位置，默认为 src/components; 有需要也可以加上 view 目录
      dirs: ['src/components/'],
      resolvers: [ElementPlusResolver(), IconsResolver(), VueUseComponentsResolver()],
    }),
    Icons({
      compiler: 'vue3',
      autoInstall: true,
    }),
    ViteFonts({
      google: {
        families: ['Open Sans', 'Montserrat', 'Fira Sans'],
      },
    }),
    VueI18n({
      include: [resolve(__dirname, '../locales/**')],
    }),
    WindiCSS(),
  ];
};