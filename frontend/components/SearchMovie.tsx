import { Fragment, useEffect, useState } from "react";
import { Combobox, Transition } from "@headlessui/react";
import { BiSearchAlt } from "react-icons/bi";
import axios from "axios";
import Movie from "../types/Movie";
import { useNavigate } from "react-router-dom";
interface SearchAllProps {
  closeModal: () => void;
}
const SearchAll: React.FC<SearchAllProps> = ({ closeModal }) => {
  const navigate = useNavigate();
  const [groups, setGroups] = useState<Movie[] | []>([]);
  const [selected, setSelected] = useState(null);
  const [query, setQuery] = useState("");

  async function getTitle() {
    try {
      const res = await axios.get(
        `http://localhost:8080/movies/title?title=${query}`
      );
      setGroups(res.data);
    } catch (e) {
      setGroups([]);
    }
  }
  useEffect(() => {
    if (selected != null) {
      closeModal();
      navigate(`/${selected}`);
      window.location.reload();
    }
    if (query == "") {
      setGroups([]);
    } else {
      getTitle();
    }
  }, [query]);

  return (
    <>
      <Combobox value={selected} onChange={setSelected}>
        <div className="relative mt-1">
          <div className="relative w-full">
            <div className="flex absolute inset-y-0 left-0 items-center pl-3 pointer-events-none">
              <BiSearchAlt className="text-black text-lg" />
            </div>
            <Combobox.Input
              className="h-14 left-0 flex items-center border  text-sm rounded-lg w-full pl-10 p-2.5 text-black bg-[#ffffff] border-gray-200 outline-none placeholder:text-black"
              onChange={(event) => setQuery(event.target.value)}
              placeholder="Search Links..."
            />
          </div>
          <Transition
            as={Fragment}
            leave="transition ease-in duration-100"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
            afterLeave={() => setQuery("")}
          >
            <Combobox.Options className="absolute mt-1 max-h-60 w-full overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm">
              {groups.length == 0 && query !== "" ? (
                <div className="relative cursor-default select-none py-2 px-4 text-gray-700">
                  Nothing found.
                </div>
              ) : (
                groups.map((movie) => (
                  <Combobox.Option
                    key={movie.title}
                    className={({ active }) =>
                      `relative cursor-default select-none py-2 pl-10 pr-4 ${
                        active ? "bg-blue-600 text-white" : "text-gray-900"
                      }`
                    }
                    value={movie.title}
                  >
                    {({ selected, active }) => (
                      <>
                        <span
                          className={`block truncate ${
                            selected ? "font-medium" : "font-normal"
                          }`}
                        >
                          {movie.title}
                        </span>
                      </>
                    )}
                  </Combobox.Option>
                ))
              )}
            </Combobox.Options>
          </Transition>
        </div>
      </Combobox>
    </>
  );
};

export default SearchAll;
