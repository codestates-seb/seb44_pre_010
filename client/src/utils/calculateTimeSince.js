export function calculateTimeSince(createdAt) {
  const currentDate = new Date();
  const registrationDate = new Date(createdAt);

  const timeDiff = currentDate - registrationDate;
  const daysDiff = Math.floor(timeDiff / (1000 * 60 * 60 * 24)) + 1;

  if (daysDiff === 1) return `since days`;
  else if (daysDiff < 32) return `${daysDiff} days`;
  else if (daysDiff < 365) return `${Math.floor(daysDiff / 31)} months`;

  return `${Math.floor(daysDiff / 365)} years, ${daysDiff % 365} months`;
}
