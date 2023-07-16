type Key = 'id' | 'name' | 'nickname' | 'end-point';

export const useMemberStorage = () => {
  const getItem = (key: Key) => localStorage.getItem(key);
  const setItem = (key: Key, item: string) => localStorage.setItem(key, item);
  const setItems = (items: [Key, string][]) => {
    items.forEach(([key, item]) => setItem(key, item));
  };
  const hasSignInInfo =
    typeof getItem('id') === 'string' &&
    typeof getItem('name') === 'string' &&
    typeof getItem('nickname') === 'string';

  return { localStorage, hasSignInInfo, getItem, setItem, setItems };
};
