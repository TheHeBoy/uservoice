/**
 * 配置浏览器本地存储的方式，可直接存储对象数组。
 */

import WebStorageCache from 'web-storage-cache';

export const CACHE_KEY = {
  IS_DARK: 'isDark',
  USER: 'user',
  LANG: 'lang',
};

type CacheType = 'localStorage' | 'sessionStorage';

export const useCache = (type: CacheType = 'localStorage') => {
  const wsCache: WebStorageCache = new WebStorageCache({
    storage: type,
  });

  return {
    wsCache,
  };
};
