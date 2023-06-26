import styled from 'styled-components';
import { ReactComponent as SearchIcon } from '../../assets/icons/search.svg';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const SearchForm = styled.form`
  padding: 0 0.5rem;
  display: flex;
  align-items: center;
  flex-grow: 1;
  box-sizing: border-box;
`;

const SearchFormInner = styled.div`
  position: relative;
  flex-grow: 1;
`;

const SearchInput = styled.input`
  border: 1px solid var(--black-200);
  background-color: white;
  color: var(--black-700);
  font-size: 13px;
  border-radius: 3px;
  line-height: calc((13 + 2) / 13);
  padding: 0.5rem 0.6rem 0.5rem 2rem;
  width: 100%;
  margin: 0;
  box-sizing: border-box;

  &::placeholder {
    color: var(--black-200);
    font-size: 12px;
  }
`;

const SearchButton = styled.button`
  background-color: transparent;
  border: none;
  cursor: pointer;
  padding: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  outline: none;
  pointer-events: auto;
`;

const SearchInputIcon = styled(SearchIcon)`
  color: var(--black-400);
  margin-top: -9px;
  position: absolute;
  left: 0.7rem;
  top: 50%;
`;

export default function SearchBar() {
  const [searchKeyword, setSearchKeyword] = useState('');
  const navigate = useNavigate();

  const onHandleChangeKeyword = (e) => {
    setSearchKeyword(e.target.value);
  };
  const handleEnterSearch = (e) => {
    e.preventDefault(); // 새로고침 방지
    if (searchKeyword.trim() !== '') {
      // 공백 방지
      navigate(`/search?keyword=${encodeURIComponent(searchKeyword)}`);
    }
  };
  const handleClickSearch = (e) => {
    e.preventDefault(); // 새로고침 방지
    if (searchKeyword.trim() !== '') {
      // 공백 방지
      navigate(`/search?keyword=${encodeURIComponent(searchKeyword)}`);
    }
  };
  return (
    <SearchForm
      id="search"
      role="search"
      action=""
      autoComplete="off"
      onSubmit={handleEnterSearch}
    >
      <SearchFormInner>
        <SearchButton type="button" onClick={handleClickSearch}>
          <SearchInputIcon />
        </SearchButton>
        <SearchInput
          type="text"
          role="combobox"
          placeholder="Search..."
          autoComplete="off"
          maxLength="240"
          value={searchKeyword}
          onChange={onHandleChangeKeyword}
          aria-label="Search"
          aria-expanded="false"
          aria-controls="top-serach"
        />
      </SearchFormInner>
    </SearchForm>
  );
}
