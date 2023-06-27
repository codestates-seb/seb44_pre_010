import { useState } from 'react';

const updateProfile = async (userId, newName, accessToken) => {
  const response = await fetch(
    `http://ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com:8080/api/v1/users/${userId}`,
    {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${accessToken}`,
      },
      body: JSON.stringify({ name: newName }),
    },
  );
  if (response.ok) {
    console.log('프로필 업데이트 성공');
  } else {
    console.error('프로필 업데이트 오류:', response.status);
  }
};

export const useEditUser = () => {
  const [isLoading, setIsLoading] = useState(false);

  const editUser = async (userId, newName, accessToken) => {
    setIsLoading(true);
    try {
      await updateProfile(userId, newName, accessToken);
    } catch (error) {
      console.error('프로필 업데이트 오류:', error);
    }
    setIsLoading(false);
  };

  return {
    editUser,
    isLoading,
  };
};
