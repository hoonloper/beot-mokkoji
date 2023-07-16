export const useMemberStorage = () => {
  const getItem = (key: string) => localStorage.getItem(key);
  const setItem = (key: string, item: string) =>
    localStorage.setItem(key, item);
  const setItems = (items: [string, string][]) => {
    items.forEach(([key, item]) => setItem(key, item));
  };
  const hasSignInInfo =
    typeof getItem('id') === 'string' &&
    typeof getItem('name') === 'string' &&
    typeof getItem('nickname') === 'string';

  return { localStorage, hasSignInInfo, getItem, setItem, setItems };
};
