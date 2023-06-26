export function changeTimeFormat(createdAt) {
  const date = new Date(createdAt);
  const now = new Date();
  const diffInMilliseconds = now - date;
  const diffInHours = Math.floor(diffInMilliseconds / (1000 * 60 * 60));

  if (diffInHours < 24) {
    return `${diffInHours} hours ago`;
  } else {
    return date.toLocaleDateString('en-US', {
      year: 'numeric',
      month: 'short',
      day: 'numeric',
    });
  }
}
