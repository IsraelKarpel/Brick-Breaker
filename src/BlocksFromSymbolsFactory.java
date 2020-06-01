import java.util.Map;
/**
* holds the lists of the blocks.
*/
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
    * returns true if 's' is a valid space symbol.
    * @param s the symbol to check
        * @return boolean.
        **/
        public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
        }
        /**
        * returns true if 's' is a valid block symbol.
        * @param s the symbol to check
        * @return boolean.
        **/
        public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);
            }
        /**
        * Return a block according to the definitions associated.
        * with symbol s. The block will be located at position (xpos, ypos).
        * @param s the symbol
        * @param xpos the x coardinates.
        * @param ypos the y coardinates
        * @return new block.
        **/
        public Block getBlock(String s, int xpos, int ypos) {
            BlockCreator help = new BlockHelper();
            if (isBlockSymbol(s)) {
            help = blockCreators.get(s);
            Block b = help.create(xpos, ypos);
            b.setNumber(help.getHit());
            b.setKColor(help.getKColor());
            b.setKImage(help.getKImage());
            b.setStroke(help.getStroke());
            return b;
            } else {
            return null;
            }
        }
        /**
        * Returns the width in pixels associated with the given spacer-symbol.
        * @param s the symbol.
        * @return int the width.
        **/
        public int getSpaceWidth(String s) {
            return this.spacerWidths.get(s);
        }
        /**
        * set the list of the spacers.
        * @param sp the list.
        **/
        public void setSpacers(Map<String, Integer> sp) {
        this.spacerWidths = sp;
        }
        /**
        * set the list of the blocks.
        * @param bl the list.
        **/
        public void setBlocks(Map<String, BlockCreator> bl) {
            this.blockCreators = bl;
        }
        /**
        * returns the list of the spacers.
        * @return the list.
        **/
        public Map<String, Integer> getSpacers() {
        return this.spacerWidths;
        }
        /**
        * returns the list of the blocks.
        * @return the list.
        **/
        public Map<String, BlockCreator> getBlocks() {
        return this.blockCreators;
    }
}