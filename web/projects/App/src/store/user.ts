import store from './index';
import { getAccessToken, removeToken } from '@/utils/auth';
import { CACHE_KEY, useCache } from '@/hooks/useCache';
import { getUserInfo, logout } from '@/api/login';

const { wsCache } = useCache();

export const useUserStore = defineStore('app-user', {
  state: () => ({
    user: { id: -1, avatar: '', nickname: '', userType: -1 },
  }),
  getters: {},
  actions: {
    async setUserInfoAction() {
      let token = getAccessToken();
      if (!token) {
        this.resetState();
        return false;
      }

      let userInfo = wsCache.get(CACHE_KEY.USER);
      if (!userInfo || this.user.id == -1) {
        userInfo = await getUserInfo();
      }

      wsCache.set(CACHE_KEY.USER, userInfo);
      this.user = userInfo;
      return true;
    },
    async loginOut() {
      await logout();
      removeToken();
      wsCache.delete(CACHE_KEY.USER);
      this.resetState();
    },
    resetState() {
      this.user = {
        id: -1,
        avatar: '',
        nickname: '',
        userType: -1,
      };
    },
    isLogin() {
      return wsCache.get(CACHE_KEY.USER) && getAccessToken() && this.user.id != -1;
    },
  },
});

export const useUserStoreWithOut = () => {
  return useUserStore(store);
};