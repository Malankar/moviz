import { BiSearchAlt } from "react-icons/bi";
// import { MdLogout } from "react-icons/md";
import Auth from "./Auth"
function Navbar() {
  return (
    <div className="flex justify-center items-center p-5 max-w-7xl m-auto space-x-4">
      {/* left side - site logo  */}
      <div className="flex items-center">
        <img src="/logo.png" alt="logo" className="shadow-lg rounded-full" width={50} height={50} />
      </div>
      {/* middle search bar */}
      <div className="grow w-14 mr-5">
        <div className="relative w-full">
          <div className="flex absolute inset-y-0 left-0 items-center pl-3 pointer-events-none">
            <BiSearchAlt className="text-black text-lg" />
          </div>
          <button className="h-12 left-0 flex items-center border  text-sm rounded-lg w-full pl-10 p-2.5  bg-[#ffffff] text-black border-[#d2d0d0] outline-none ">
            Search Links...
          </button>
          <button
            type="button"
            className="absolute inset-y-0 right-0 items-center pr-3 hidden text-white md:flex"
          >
            <kbd className="px-2 py-1.5 text-xs font-semibold border rounded-lg bg-gray-200 text-gray-900 border-gray-500">
              Ctrl+K
            </kbd>
          </button>
        </div>
      </div>
      {/* right side - refresh to sync database icon + grid to list icon + change theme icon + user icon */}
      <Auth/>
      
      {/* <div className="relative">
        <div className="flex-none">
          <div className="cursor-pointer flex items-center justify-around py-1.5 px-1 shadow-md rounded-lg">
            <div className="rounded-full bg-black text-white px-3 py-1.5 font-bold">A</div>
            <p className="text-gray-900 select-none text-lg font-semibold pl-2 pr-2">
              Avdhut
            </p>
          </div>
        </div>
        <div
          id="dropdown"
          className="absolute cursor-pointer z-10 w-32 right-4/4 mt-2 rounded-lg shadow bg-gray-800"
        >
          <ul
            className="py-1 text-sm text-gray-400 hover:text-white"
            aria-labelledby="dropdownDefault"
          >
            <li className="flex items-center">
              <MdLogout className="ml-2 h-5 w-5" />
              <span className="block py-2 px-4 ">Sign Out</span>
            </li>
          </ul>
        </div>
      </div> */}
    </div>
  );
}

export default Navbar;
