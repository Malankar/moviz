import { Disclosure } from "@headlessui/react";
import { BsChevronDown } from "react-icons/bs";
function Accordian() {
  return (
    <div className="w-full mt-5">
      <div className="mx-auto w-full max-w-md rounded-2xl bg-white p-2">
        <div className="bg-white shadow-md rounded-lg mb-5">
          <Disclosure defaultOpen>
            {({ open }) => (
              <>
                <Disclosure.Button className="flex w-full items-center justify-between rounded-lg bg-white px-4 py-2 text-left text-sm font-medium text-purple-900 hover:bg-gray-200 focus:outline-none focus-visible:ring focus-visible:ring-purple-500 focus-visible:ring-opacity-75">
                  <div className="flex items-center">
                    <BsChevronDown
                      className={`${
                        open ? "rotate-180 transform mr-3" : "mr-3"
                      } h-5 w-5 text-purple-500`}
                    />
                    <span className="text-lg">Languages</span>
                  </div>
                  <p className="text-gray-400 hover:text-black">Clear</p>
                </Disclosure.Button>
                <Disclosure.Panel className="px-4 pt-4 pb-2 text-sm text-gray-500">
                  <div className="grid grid-rows-3 grid-flow-col gap-3">
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      English
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Hindi
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Telugu
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Hindi
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Gujrati
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Haryanavi
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Marathi
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Malayalam
                    </span>
                  </div>
                </Disclosure.Panel>
              </>
            )}
          </Disclosure>
        </div>
        <div className="bg-white shadow-md rounded-lg mb-5">
          <Disclosure defaultOpen>
            {({ open }) => (
              <>
                <Disclosure.Button className="flex w-full items-center justify-between rounded-lg bg-white px-4 py-2 text-left text-sm font-medium text-purple-900 hover:bg-gray-200 focus:outline-none focus-visible:ring focus-visible:ring-purple-500 focus-visible:ring-opacity-75">
                  <div className="flex items-center">
                    <BsChevronDown
                      className={`${
                        open ? "rotate-180 transform mr-3" : "mr-3"
                      } h-5 w-5 text-purple-500`}
                    />
                    <span className="text-lg">Genres</span>
                  </div>
                  <p className="text-gray-400 hover:text-black">Clear</p>
                </Disclosure.Button>
                <Disclosure.Panel className="px-4 pt-4 pb-2 text-sm text-gray-500">
                  <div className="grid gap-2 overflow-x-hidden overflow-y-auto h-96">
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Drama
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Comedy
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Action
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Thriller
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Adventure
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Romantic
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Family
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Animation
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Biography
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Classic
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Crime
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Fantasy
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Horror
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Musical
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Mystery
                    </span>
                    <span className="bg-blue-0 border-[1px] border-gray-400 text-black text-xs font-semibold mr-2 px-2.5 py-2 rounded hover:bg-blue-600 hover:text-white cursor-pointer">
                      Sci-Fi
                    </span>
                  </div>
                </Disclosure.Panel>
              </>
            )}
          </Disclosure>
        </div>
        
      </div>
    </div>
  );
}

export default Accordian;
