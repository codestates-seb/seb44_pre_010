import { useState } from 'react';

const useDeleteUser = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const deleteUser = async (userId, accessToken) => {
    setIsLoading(true);

    try {
      const response = await fetch(
        `http://ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com:8080/api/v1/users/${userId}`,
        {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${accessToken}`,
          },
        },
      );

      if (response.ok) {
        setIsLoading(false);
        return true;
      } else {
        throw new Error('Failed to delete user');
      }
    } catch (error) {
      setIsLoading(false);
      setError(error);
      throw new Error('Failed to connect to the server');
    }
  };

  return {
    deleteUser,
    isLoading,
    error,
  };
};

export default useDeleteUser;
