import { Fragment, useEffect, useState } from "react";
import { Combobox, Transition } from "@headlessui/react";
import { BiSearchAlt } from "react-icons/bi";
import { BsArrowReturnLeft, BsArrowReturnRight } from "react-icons/bs";

const SearchAll = () => {
  const [groups, setGroups] = useState([]);
  const [selected, setSelected] = useState(groups[0]);
  const [query, setQuery] = useState("");

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
            <Combobox.Options className="absolute mt-1 max-h-60 w-full overflow-auto rounded-md bg-[#ffffff] py-1 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm">
              nothing here
            </Combobox.Options>
          </Transition>
        </div>
      </Combobox>
    </>
  );
};

export default SearchAll;
