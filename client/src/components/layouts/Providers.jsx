import RTKStore from '../../redux/store';
import { Provider as RTKProvider } from 'react-redux';

export default function Providers({ children }) {
  return <RTKProvider store={RTKStore}>{children}</RTKProvider>;
}
