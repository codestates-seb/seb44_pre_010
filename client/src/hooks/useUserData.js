import { useState, useEffect } from 'react';

export function useUserData(userId, accessToken) {
  const [userData, setUserData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(
          `http://ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com:8080/api/v1/users/${userId}`,
          {
            headers: {
              Authorization: `Bearer ${accessToken}`,
            },
          },
        );
        if (!response.ok) {
          throw new Error('Failed to fetch user data.');
        }
        const data = await response.json();
        setUserData(data);
      } catch (error) {
        console.error(error);
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [userId, accessToken]);

  return {
    data: userData,
    isLoading: loading,
    error: error,
  };
}
